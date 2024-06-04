import React, { useState } from 'react';
import './RoomDetails.css';

const RoomDetails = ({ room, onBack }) => {
  const [devices, setDevices] = useState(room.devices);
  const [showAddDeviceForm, setShowAddDeviceForm] = useState(false);
  const [newDeviceName, setNewDeviceName] = useState('');
  const [newDeviceType, setNewDeviceType] = useState('');

  const handleAddDevice = () => {
    setDevices([
      ...devices,
      { name: newDeviceName, type: newDeviceType }
    ]);
    setNewDeviceName('');
    setNewDeviceType('');
    setShowAddDeviceForm(false);
  };

  return (
    <div className="room-details">
      <div className="room-details-header">
        <h2>{room.roomID}</h2>
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
              <button className="view-more-button">View More</button>
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
                <option value="Lamp">Lamp</option>
                <option value="TV">TV</option>
                <option value="Air Conditioner">Air Conditioner</option>
                <option value="Heater">Heater</option>
                <option value="Fan">Fan</option>
              </select>
              <button className="save-device-button" onClick={handleAddDevice}>Save</button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default RoomDetails;
