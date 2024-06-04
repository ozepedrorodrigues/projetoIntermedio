import React, { useState } from 'react';
import { MdBlindsClosed, MdBlinds } from 'react-icons/md';
import './DeviceDetails.css';
import ReadingList from './ReadingList';

const DeviceDetails = ({ device, roomName, onBack }) => {
  const [status, setStatus] = useState(device.status || 'active');
  const [showAddSensorForm, setShowAddSensorForm] = useState(false);
  const [newSensorType, setNewSensorType] = useState('');
  const [newSensorModel, setNewSensorModel] = useState('');
  const [showAddActuatorForm, setShowAddActuatorForm] = useState(false);
  const [newActuatorType, setNewActuatorType] = useState('');
  const [newActuatorModel, setNewActuatorModel] = useState('');
  const [actuatorValues, setActuatorValues] = useState(
    device.actuators.reduce((acc, actuator) => {
      acc[actuator.id] = actuator.initialValue || 0;
      return acc;
    }, {})
  );
  const [operateDisabled, setOperateDisabled] = useState(true);
  const [showPopup, setShowPopup] = useState(false);
  const [popupMessage, setPopupMessage] = useState('');
  const [previousActuatorValues, setPreviousActuatorValues] = useState(
    device.actuators.reduce((acc, actuator) => {
      acc[actuator.id] = actuator.initialValue || 0;
      return acc;
    }, {})
  );
  const [showReadingsForm, setShowReadingsForm] = useState(false);
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');
  const [getReadingsDisabled, setGetReadingsDisabled] = useState(false);
  const [showReadingsList, setShowReadingsList] = useState(false);
  const [readings, setReadings] = useState([]);

  const sensorModels = {
    LightSensor: ['Model A', 'Model B'],
    TemperatureSensor: ['Model C', 'Model D'],
    ImageSensor: ['Model E', 'Model F'],
    AirFlowSensor: ['Model G', 'Model H']
  };

  const actuatorModels = {
    LightActuator: ['Model A1', 'Model B1'],
    TemperatureActuator: ['Model C1', 'Model D1'],
    ImageActuator: ['Model E1', 'Model F1'],
    AirFlowActuator: ['Model G1', 'Model H1'],
    'Actuator of Blind Roller': ['Model I1']
  };

  const handleDeactivate = () => {
    setStatus('inactive');
    setOperateDisabled(true); // Desabilitar botão "Operate" quando desativado
  };

  const handleAddSensor = () => {
    const newSensor = {
      id: device.sensors.length + 1,
      model: newSensorModel,
      type: newSensorType,
      lastReading: 'N/A',
      unit: 'N/A'
    };
    device.sensors.push(newSensor);
    setNewSensorType('');
    setNewSensorModel('');
    setShowAddSensorForm(false);
  };

  const handleAddActuator = () => {
    const newActuator = {
      id: device.actuators.length + 1,
      model: newActuatorModel,
      type: newActuatorType
    };
    device.actuators.push(newActuator);
    setNewActuatorType('');
    setNewActuatorModel('');
    setShowAddActuatorForm(false);
  };

  const handleOperateActuator = (actuator) => {
    const previousValue = previousActuatorValues[actuator.id];
    const newValue = parseInt(actuatorValues[actuator.id], 10);

    let action = 'Adjusting...';
    if (newValue > previousValue) {
      action = 'Opening...';
    } else if (newValue < previousValue) {
      action = 'Closing...';
    }

    setPopupMessage(action);
    setShowPopup(true);
    setTimeout(() => {
      setPopupMessage('Success!');
      setTimeout(() => setShowPopup(false), 2000);
    }, 2000);
    setOperateDisabled(true);
  };

  const handleSliderChange = (actuator, value) => {
    if (status === 'inactive') return; // Não operar se o dispositivo estiver inativo
    setPreviousActuatorValues({ ...previousActuatorValues, [actuator.id]: actuatorValues[actuator.id] }); // Armazena o valor anterior
    setActuatorValues({ ...actuatorValues, [actuator.id]: parseInt(value, 10) });
    setOperateDisabled(false);
    setPopupMessage('');
  };

  const handleGetReadings = () => {
    setShowReadingsForm(true);
    setGetReadingsDisabled(true);
  };

  const handleReadingsSubmit = () => {
    const mockReadings = [
      { id: 1, sensorType: 'LightSensor', timestamp: '2024-06-01T12:00:00' },
      { id: 2, sensorType: 'TemperatureSensor', timestamp: '2024-06-01T12:05:00' },
      { id: 3, sensorType: 'ImageSensor', timestamp: '2024-06-01T12:10:00' },
    ];
    setReadings(mockReadings);
    setShowReadingsForm(false);
    setShowReadingsList(true);
  };

  const handleCancelReadings = () => {
    setShowReadingsForm(false);
    setGetReadingsDisabled(false);
  };

  const handleBackToDeviceDetails = () => {
    setShowReadingsList(false);
    setGetReadingsDisabled(false);  // Reativar o botão "Get Readings"
  };

  if (showReadingsList) {
    return (
      <ReadingList
        deviceID={device.deviceID}
        readings={readings}
        startDate={startDate}
        endDate={endDate}
        onBack={handleBackToDeviceDetails}
      />
    );
  }

  return (
    <div className="device-details">
      <div className="device-details-header">
        <h2>Device ID: {device.deviceID}</h2>
        <button className="back-button" onClick={onBack}>Back</button>
      </div>
      <p><strong>Name:</strong> {device.name}</p>
      <p><strong>Type:</strong> {device.type}</p>
      <p><strong>Room:</strong> {roomName}</p>
      <p><strong>Status:</strong> {status}
        {status === 'active' && (
          <button className="deactivate-button" onClick={handleDeactivate}>Deactivate</button>
        )}
      </p>
      <button
        className={`get-readings-button ${getReadingsDisabled ? 'disabled' : ''}`}
        onClick={handleGetReadings}
        disabled={getReadingsDisabled}
      >
        Get Readings
      </button>
      {showReadingsForm && (
        <div className="readings-form">
          <label>
            Start Date:
            <input type="datetime-local" value={startDate} onChange={(e) => setStartDate(e.target.value)} />
          </label>
          <label>
            End Date:
            <input type="datetime-local" value={endDate} onChange={(e) => setEndDate(e.target.value)} />
          </label>
          <button className="submit-readings-button" onClick={handleReadingsSubmit}>Submit</button>
          <button className="cancel-readings-button" onClick={handleCancelReadings}>Cancel</button>
        </div>
      )}
      <div className="sensor-list">
        <h3>Sensors:</h3>
        {device.sensors.map((sensor, index) => (
          <div key={index} className="sensor-item">
            <span>{sensor.model}</span>
          </div>
        ))}
        <button className="add-sensor-button" onClick={() => setShowAddSensorForm(!showAddSensorForm)}>
          {showAddSensorForm ? 'Cancel' : 'Add Sensor'}
        </button>
        {showAddSensorForm && (
          <div className="add-sensor-form">
            <select
              value={newSensorType}
              onChange={(e) => setNewSensorType(e.target.value)}
            >
              <option value="">Select Type</option>
              {Object.keys(sensorModels).map((type) => (
                <option key={type} value={type}>{type}</option>
              ))}
            </select>
            {newSensorType && (
              <select
                value={newSensorModel}
                onChange={(e) => setNewSensorModel(e.target.value)}
              >
                <option value="">Select Model</option>
                {sensorModels[newSensorType].map((model) => (
                  <option key={model} value={model}>{model}</option>
                ))}
              </select>
            )}
            <button className="save-sensor-button" onClick={handleAddSensor}>Save</button>
          </div>
        )}
      </div>

      <div className="actuator-list">
        <h3>Actuators:</h3>
        {device.actuators.map((actuator, index) => (
          <div key={index} className="actuator-item">
            <span>{actuator.model}</span>
            {actuator.type === 'Actuator of Blind Roller' ? (
              <div className="slider-container">
                <MdBlindsClosed className="slider-icon" />
                <div className="slider-wrapper">
                  <span className="slider-value">{actuatorValues[actuator.id]}%</span>
                  <input
                    type="range"
                    min="0"
                    max="100"
                    value={actuatorValues[actuator.id]}
                    onChange={(e) => handleSliderChange(actuator, e.target.value)}
                    className="slider"
                  />
                </div>
                <MdBlinds className="slider-icon" />
                <button
                  className={`operate-button ${operateDisabled || status === 'inactive' ? 'disabled' : ''}`}
                  onClick={() => handleOperateActuator(actuator)}
                  disabled={operateDisabled || status === 'inactive'}
                >
                  Operate
                </button>
              </div>
            ) : (
              <button className="operate-button disabled" disabled>Operate</button>
            )}
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
              {Object.keys(actuatorModels).map((type) => (
                <option key={type} value={type}>{type}</option>
              ))}
            </select>
            {newActuatorType && (
              <select
                value={newActuatorModel}
                onChange={(e) => setNewActuatorModel(e.target.value)}
              >
                <option value="">Select Model</option>
                {actuatorModels[newActuatorType].map((model) => (
                  <option key={model} value={model}>{model}</option>
                ))}
              </select>
            )}
            <button className="save-actuator-button" onClick={handleAddActuator}>Save</button>
          </div>
        )}
      </div>
      {showPopup && (
        <div className="popup">
          <div className="popup-content">
            <span>{popupMessage}</span>
          </div>
        </div>
      )}
    </div>
  );
};

export default DeviceDetails;
