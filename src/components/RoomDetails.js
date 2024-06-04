import React, { useEffect, useState } from 'react';
import './RoomDetails.css';
import DeviceDetails from "../../src/components/DeviceDetails";

const RoomDetails = ({room, onBack}) => {


    // Device
    const [selectedDevice, setSelectedDevice] = useState(null);
    const [devices, setDevices] = useState([]);
    const [showAddDeviceForm, setShowAddDeviceForm] = useState(false);
    const [newDeviceName, setNewDeviceName] = useState('');
    const [newDeviceType, setNewDeviceType] = useState('');
    const [deviceTypes, setDeviceTypes] = useState([]);

    const handleAddDevice = async () => {
        const newDevice = {
            deviceName: newDeviceName,
            deviceTypeName: newDeviceType,
        };

        try {
            const response = await fetch(`http://localhost:8080/devices/room/${room.roomId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newDevice)
            });

            if (response.ok) {
                const addedDevice = await response.json();
                setDevices([...devices, addedDevice]);
                setNewDeviceName('');
                setNewDeviceType('');
                setShowAddDeviceForm(false);
            } else {
                console.error('Failed to add device:', response.statusText);
            }
        } catch (error) {
            console.error('Error adding device:', error);
        }
    };

    const handleDeviceSelect = (device) => {
        setSelectedDevice(device);
    };

    const handleDeviceBack = () => {
        setSelectedDevice(null);
    };

    // Device Types

    const fetchDeviceTypes = async () => {
        try {
            const response = await fetch('http://localhost:8080/devicetypes');

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();

            if (Array.isArray(data)) {
                const deviceTypes = data.map(deviceTypeDTO => deviceTypeDTO.deviceTypeName);
                console.log('Device types fetched successfully:', deviceTypes);
                setDeviceTypes(deviceTypes); // assuming you want to update the state with the fetched device types
            } else {
                console.error('Unexpected response format:', data);
            }
        } catch (error) {
            console.error('Error fetching device types:', error);
        }
    };

    useEffect(() => {
        fetchDeviceTypes();
    }, []);



    // devices

    const fetchDevicesForRoom = async () => {
        try {
            // Use the roomId from the room prop to fetch devices for this room
            const response = await fetch(`http://localhost:8080/devices/room/${room.roomId}`);

            if (!response.ok) {
                throw new Error(`HTTP error status: ${response.status}`);
            }

            const data = await response.json();

            // Assuming the response directly contains the devices array without _embedded
            if (Array.isArray(data)) {
                const detailedDevices = await Promise.all(data.map(async (device) => {
                    const deviceDetailsResponse = await fetch(`http://localhost:8080/devices/${device.deviceId}`);

                    if (!deviceDetailsResponse.ok) {
                        throw new Error(`HTTP error status: ${deviceDetailsResponse.status}`);
                    }

                    const deviceDetailsData = await deviceDetailsResponse.json();
                    return {...device,...deviceDetailsData}; // Merge device with its details
                }));

                setDevices(detailedDevices);
                console.log('Detailed devices fetched successfully:', detailedDevices);
            } else {
                console.error('Failed to fetch devices: Unexpected response format', data);
            }
        } catch (error) {
            console.error('Error fetching devices:', error);
        }
    };

    // Call fetchDevicesForRoom when the component mounts or when the room prop changes
    useEffect(() => {
        fetchDevicesForRoom();
    }, [room]);




    return (
        <div className="room-details">
            {selectedDevice ? (
                <DeviceDetails device={selectedDevice} roomName={room.roomName} onBack={handleDeviceBack} />
            ) : (
                <>
                    <div className="room-details-header">
                        <h2>Room ID: {room.roomName}</h2>
                        <button className="back-button" onClick={onBack}>
                            Back
                        </button>
                    </div>
                    <div className="room-details-info">
                        <p><strong>Length:</strong> {room.length}m</p>
                        <p><strong>Height:</strong> {room.height}m</p>
                        <p><strong>Width:</strong> {room.width}m</p>
                        <p><strong>Floor:</strong> {room.floor}</p>
                        <div className="device-list">
                            <h3>Devices:</h3>
                            {devices && devices.map((device, index) => (
                                <div key={index} className="device-item">
                                    <span>{device.deviceName}</span>
                                    <button className="device-view-more-button" onClick={() => handleDeviceSelect(device)}>View More</button>
                                </div>
                            ))}
                            <button className="add-device-button" onClick={() => setShowAddDeviceForm(!showAddDeviceForm)}>
                                {showAddDeviceForm ? 'Cancel' : 'Add Device'}
                            </button>
                            {showAddDeviceForm && (
                                <div className="add-device-form">
                                    <input
                                        type="text"
                                        placeholder="Device Name"
                                        value={newDeviceName}
                                        onChange={(e) => setNewDeviceName(e.target.value)}
                                    />
                                    <select
                                        value={newDeviceType}
                                        onChange={(e) => setNewDeviceType(e.target.value)}
                                    >
                                        <option value="">Select Type</option>
                                        {deviceTypes.map((type, index) => (
                                            <option key={index} value={type}>{type}</option>
                                        ))}
                                    </select>
                                    <button className="save-device-button" onClick={handleAddDevice}>Save</button>
                                </div>
                            )}
                        </div>
                    </div>
                </>
            )}
        </div>
    );
};

export default RoomDetails;
