import React, { useEffect, useState } from 'react';
import './DeviceDetails.css';
import { MdBlinds, MdBlindsClosed } from "react-icons/md";

const DeviceDetails = ({ device, roomName, onBack }) => {
    const [status, setStatus] = useState(device.deviceStatus);
    const [actuators, setActuators] = useState([]);
    const [actuatorTypes, setActuatorTypes] = useState([]);
    const [actuatorModels, setActuatorModels] = useState([]);
    const [showAddActuatorForm, setShowAddActuatorForm] = useState(false);
    const [newActuatorModel, setNewActuatorModel] = useState('');
    const [newActuatorType, setNewActuatorType] = useState('');

    const handleAddActuator = async () => {
        const newActuator = {
            model: newActuatorModel,
            type: newActuatorType
        };

        try {
            const response = await fetch(`http://localhost:8080/actuators/device/${device.deviceId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newActuator)
            });

            if (response.ok) {
                const addedActuator = await response.json();
                setActuators([...actuators, addedActuator]);
                setNewActuatorType('');
                setNewActuatorModel('');
                setShowAddActuatorForm(false);
            } else {
                console.error('Failed to add actuator:', response.statusText);
            }
        } catch (error) {
            console.error('Error adding actuator:', error);
        }
    };

    const handleDeactivate = async (deviceId) => {
        try {
            const response = await fetch(`http://localhost:8080/devices/${deviceId}/deactivate`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            if (response.ok) {
                setStatus(false);
            } else {
                console.error('Failed to deactivate device:', response.statusText);
            }
        } catch (error) {
            console.error('Error deactivating device:', error);
        }
    };

    useEffect(() => {
        setStatus(device.deviceStatus);
    }, [device.deviceStatus]);

    const fetchActuatorOfDevice = async () => {
        try {
            const response = await fetch(`http://localhost:8080/actuators/device/${device.deviceId}`);

            if (!response.ok) {
                throw new Error(`HTTP error status: ${response.status}`);
            }

            const data = await response.json();

            if (Array.isArray(data)) {
                const detailedActuators = await Promise.all(data.map(async (actuator) => {
                    const actuatorDetailsResponse = await fetch(`http://localhost:8080/actuators/${actuator.actuatorId}`);

                    if (!actuatorDetailsResponse.ok) {
                        throw new Error(`HTTP error status: ${actuatorDetailsResponse.status}`);
                    }

                    const actuatorDetailsData = await actuatorDetailsResponse.json();
                    return { ...actuator, ...actuatorDetailsData };
                }));

                setActuators(detailedActuators);
                console.log('Detailed actuators fetched successfully:', detailedActuators);
            } else {
                console.error('Failed to fetch actuators: Unexpected response format', data);
            }
        } catch (error) {
            console.error('Error fetching actuators:', error);
        }
    };

    useEffect(() => {
        fetchActuatorOfDevice();
    }, [device.deviceId]);

    const fetchActuatorTypes = async () => {
        try {
            const response = await fetch('http://localhost:8080/actuatortypes');

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();

            if (Array.isArray(data)) {
                const actuatorTypes = data.map(actuatorTypeDTO => actuatorTypeDTO.actuatorTypeName);
                console.log('Actuator types fetched successfully:', actuatorTypes);
                setActuatorTypes(actuatorTypes);
            } else {
                console.error('Unexpected response format:', data);
            }
        } catch (error) {
            console.error('Error fetching actuator types:', error);
        }
    };

    useEffect(() => {
        fetchActuatorTypes();
    }, []);

    const fetchActuatorModels = async (type) => {
        try {
            const response = await fetch(`http://localhost:8080/actuatormodels/type/${type}`);

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();

            if (data._embedded && Array.isArray(data._embedded.actuatorModelNameDTOList)) {
                const models = data._embedded.actuatorModelNameDTOList.map(model => model.actuatorModelName);
                console.log('Actuator models fetched successfully:', models);
                setActuatorModels(models);
            } else {
                console.error('Unexpected response format:', data);
            }
        } catch (error) {
            console.error('Error fetching actuator models:', error);
        }
    };

    useEffect(() => {
        if (newActuatorType) {
            fetchActuatorModels(newActuatorType);
        }
    }, [newActuatorType]);

    return (
        <div className="device-details">
            <div className="device-details-header">
                <h2>Device ID: {device.deviceName}</h2>
                <button className="back-button" onClick={onBack}>
                    Back
                </button>
            </div>
            <div className="device-details-info">
                <p><strong>Type:</strong> {device.deviceTypeName}</p>
                <p><strong>Room:</strong> {roomName}</p>
                <p><strong>Status:</strong> {status ? 'active' : 'inactive'}
                    {status && (
                        <button className="deactivate-button" onClick={() => handleDeactivate(device.deviceId)}>Deactivate</button>
                    )}
                </p>
            </div>
            <div className="actuator-list">
                <h3>Actuators:</h3>
                {actuators && actuators.map((actuator, index) => (
                    <div key={index} className="device-item">
                        <span>{actuator.actuatorId}</span>
                    </div>
                ))}
                <button className="add-actuator-button" onClick={() => setShowAddActuatorForm(!showAddActuatorForm)}>
                    {showAddActuatorForm ? 'Cancel' : 'Add Actuator'}
                </button>
                {showAddActuatorForm && (
                    <div className="add-actuator-form">
                        <select
                            value={newActuatorType}
                            onChange={(e) => setNewActuatorType(e.target.value)}
                        >
                            <option value="">Select Type</option>
                            {actuatorTypes.map((type, index) => (
                                <option key={index} value={type}>{type}</option>
                            ))}
                        </select>
                        {newActuatorType && (
                            <select
                                value={newActuatorModel}
                                onChange={(e) => setNewActuatorModel(e.target.value)}
                            >
                                <option value="">Select Model</option>
                                {actuatorModels.map((model, index) => (
                                    <option key={index} value={model}>{model}</option>
                                ))}
                            </select>
                        )}
                        <button className="save-actuator-button" onClick={handleAddActuator}>Save</button>
                    </div>
                )}
            </div>
        </div>
    );
};

export default DeviceDetails;
