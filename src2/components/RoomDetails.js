import React, { useState } from 'react';
import './RoomDetails.css';
import DeviceDetails from './DeviceDetails';

const RoomDetails = ({ room, onBack }) => {
  const [selectedDevice, setSelectedDevice] = useState(null);
  const [devices, setDevices] = useState(room.devices);
  const [showAddDeviceForm, setShowAddDeviceForm] = useState(false);
  const [newDeviceName, setNewDeviceName] = useState('');
  const [newDeviceType, setNewDeviceType] = useState('');

  const handleAddDevice = () => {
    const newDevice = {
      deviceID: devices.length + 1,
      name: newDeviceName,
      type: newDeviceType,
      status: 'active',
      room: room.name,
      sensors: [],
      actuators: []
    };
    setDevices([...devices, newDevice]);
    setNewDeviceName('');
    setNewDeviceType('');
    setShowAddDeviceForm(false);
  };

  const handleDeviceSelect = (device) => {
    setSelectedDevice(device);
  };

  const handleDeviceBack = () => {
    setSelectedDevice(null);
  };

  return (
    <div className="room-details">
      {selectedDevice ? (
        <DeviceDetails device={selectedDevice} roomName={room.name} onBack={handleDeviceBack} />
      ) : (
        <>
          <div className="room-details-header">
            <h2>Room ID: {room.roomID}</h2>
            <button className="back-button" onClick={onBack}>
              Back
            </button>
          </div>
          <div className="room-details-info">
            <p><strong>Room Name:</strong> {room.name}</p>
            <p><strong>Length:</strong> {room.length}m</p>
            <p><strong>Height:</strong> {room.height}m</p>
            <p><strong>Width:</strong> {room.width}m</p>
            <p><strong>Floor:</strong> {room.floor}</p>
            <div className="device-list">
              <h3>Devices:</h3>
              {devices.map((device, index) => (
                <div key={index} className="device-item">
                  <span>{device.name}</span>
                  <button className="view-more-button" onClick={() => handleDeviceSelect(device)}>View More</button>
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
                    <option value="Light">Light</option>
                    <option value="Entertainment">Entertainment</option>
                    <option value="Climate Control">Climate Control</option>
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
