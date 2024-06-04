import React, { useState } from 'react';
import './DeviceDetails.css';

const DeviceDetails = ({ device, roomName, onBack }) => {

  const [status, setStatus] = useState(device.status || 'active');
  // const [showAddSensorForm, setShowAddSensorForm] = useState(false);
  // const [newSensorType, setNewSensorType] = useState('');
  // const [newSensorModel, setNewSensorModel] = useState('');
  // const [showAddActuatorForm, setShowAddActuatorForm] = useState(false);
  // const [newActuatorType, setNewActuatorType] = useState('');
  // const [newActuatorModel, setNewActuatorModel] = useState('');
  // const [actuatorValues, setActuatorValues] = useState({});
  // const [selectedActuator, setSelectedActuator] = useState(null);
  // const [sliderValue, setSliderValue] = useState(0);

  // const sensorModels = {
  //   LightSensor: ['Model A', 'Model B'],
  //   TemperatureSensor: ['Model C', 'Model D'],
  //   ImageSensor: ['Model E', 'Model F'],
  //   AirFlowSensor: ['Model G', 'Model H']
  // };
  //
  // const actuatorModels = {
  //   LightActuator: ['Model A1', 'Model B1'],
  //   TemperatureActuator: ['Model C1', 'Model D1'],
  //   ImageActuator: ['Model E1', 'Model F1'],
  //   AirFlowActuator: ['Model G1', 'Model H1'],
  //   'Actuator of Blind Roller': ['Model I1']
  // };
  //
  const handleDeactivate = () => {
    setStatus('inactive');
  };
  //
  // const handleAddSensor = () => {
  //   const newSensor = {
  //     id: device.sensors.length + 1,
  //     model: newSensorModel,
  //     type: newSensorType,
  //     lastReading: 'N/A',
  //     unit: 'N/A'
  //   };
  //   device.sensors.push(newSensor);
  //   setNewSensorType('');
  //   setNewSensorModel('');
  //   setShowAddSensorForm(false);
  // };
  //
  // const handleAddActuator = () => {
  //   const newActuator = {
  //     id: device.actuators.length + 1,
  //     model: newActuatorModel,
  //     type: newActuatorType
  //   };
  //   device.actuators.push(newActuator);
  //   setNewActuatorType('');
  //   setNewActuatorModel('');
  //   setShowAddActuatorForm(false);
  // };
  //
  // const handleOperateActuator = (actuator) => {
  //   setSelectedActuator(actuator);
  //   setSliderValue(actuatorValues[actuator.id] || 0);
  // };
  //
  // const handleSaveActuatorValue = () => {
  //   setActuatorValues({ ...actuatorValues, [selectedActuator.id]: sliderValue });
  //   setSelectedActuator(null);
  // };

  return (
    <div className="device-details">
      <div className="device-details-header">
        <h2>Device ID: {device.deviceID}</h2>
        <button className="back-button" onClick={onBack}>
          Back
        </button>
      </div>
      <div className="device-details-info">
        <p><strong>Name:</strong> {device.deviceName}</p>
        <p><strong>Type:</strong> {device.deviceTypeName}</p>
        <p><strong>Room:</strong> {roomName}</p>
        <p><strong>Status:</strong> {status}
          {status === 'active' && (
            <button className="deactivate-button" onClick={handleDeactivate}>Deactivate</button>
          )}
        </p>
        {/*<div className="sensor-list">*/}
        {/*  <h3>Sensors:</h3>*/}
        {/*  {device.sensors.map((sensor, index) => (*/}
        {/*    <div key={index} className="sensor-item">*/}
        {/*      <span>{sensor.model}</span>*/}
        {/*      <button className="current-value-button">Current value: {sensor.lastReading} {sensor.unit}</button>*/}
        {/*    </div>*/}
        {/*  ))}*/}
        {/*  <button className="add-sensor-button" onClick={() => setShowAddSensorForm(!showAddSensorForm)}>*/}
        {/*    {showAddSensorForm ? 'Cancel' : 'Add Sensor'}*/}
        {/*  </button>*/}
        {/*  {showAddSensorForm && (*/}
        {/*    <div className="add-sensor-form">*/}
        {/*      <select*/}
        {/*        value={newSensorType}*/}
        {/*        onChange={(e) => setNewSensorType(e.target.value)}*/}
        {/*      >*/}
        {/*        <option value="">Select Type</option>*/}
        {/*        {Object.keys(sensorModels).map((type) => (*/}
        {/*          <option key={type} value={type}>{type}</option>*/}
        {/*        ))}*/}
        {/*      </select>*/}
        {/*      {newSensorType && (*/}
        {/*        <select*/}
        {/*          value={newSensorModel}*/}
        {/*          onChange={(e) => setNewSensorModel(e.target.value)}*/}
        {/*        >*/}
        {/*          <option value="">Select Model</option>*/}
        {/*          {sensorModels[newSensorType].map((model) => (*/}
        {/*            <option key={model} value={model}>{model}</option>*/}
        {/*          ))}*/}
        {/*        </select>*/}
        {/*      )}*/}
        {/*      <button className="save-sensor-button" onClick={handleAddSensor}>Save</button>*/}
        {/*    </div>*/}
        {/*  )}*/}
        {/*</div>*/}
        {/*<div className="actuator-list">*/}
        {/*  <h3>Actuators:</h3>*/}
        {/*  {device.actuators.map((actuator, index) => (*/}
        {/*    <div key={index} className="actuator-item">*/}
        {/*      <span>{actuator.model}</span>*/}
        {/*      {actuator.type === 'Actuator of Blind Roller' ? (*/}
        {/*        selectedActuator?.id === actuator.id ? (*/}
        {/*          <div className="slider-container">*/}
        {/*            <input*/}
        {/*              type="range"*/}
        {/*              min="0"*/}
        {/*              max="100"*/}
        {/*              value={sliderValue}*/}
        {/*              onChange={(e) => setSliderValue(e.target.value)}*/}
        {/*            />*/}
        {/*            <span>{sliderValue}%</span>*/}
        {/*            <button className="save-slider-button" onClick={handleSaveActuatorValue}>Save</button>*/}
        {/*          </div>*/}
        {/*        ) : (*/}
        {/*          <button className="operate-button" onClick={() => handleOperateActuator(actuator)}>Operate</button>*/}
        {/*        )*/}
        {/*      ) : (*/}
        {/*        <button className="operate-button" onClick={() => handleOperateActuator(actuator)}>Operate</button>*/}
        {/*      )}*/}
        {/*    </div>*/}
        {/*  ))}*/}
        {/*  <button className="add-actuator-button" onClick={() => setShowAddActuatorForm(!showAddActuatorForm)}>*/}
        {/*    {showAddActuatorForm ? 'Cancel' : 'Add Actuator'}*/}
        {/*  </button>*/}
        {/*  {showAddActuatorForm && (*/}
        {/*    <div className="add-actuator-form">*/}
        {/*      <select*/}
        {/*        value={newActuatorType}*/}
        {/*        onChange={(e) => setNewActuatorType(e.target.value)}*/}
        {/*      >*/}
        {/*        <option value="">Select Type</option>*/}
        {/*        {Object.keys(actuatorModels).map((type) => (*/}
        {/*          <option key={type} value={type}>{type}</option>*/}
        {/*        ))}*/}
        {/*      </select>*/}
        {/*      {newActuatorType && (*/}
        {/*        <select*/}
        {/*          value={newActuatorModel}*/}
        {/*          onChange={(e) => setNewActuatorModel(e.target.value)}*/}
        {/*        >*/}
        {/*          <option value="">Select Model</option>*/}
        {/*          {actuatorModels[newActuatorType].map((model) => (*/}
        {/*            <option key={model} value={model}>{model}</option>*/}
        {/*          ))}*/}
        {/*        </select>*/}
        {/*      )}*/}
        {/*      <button className="save-actuator-button" onClick={handleAddActuator}>Save</button>*/}
        {/*    </div>*/}
        {/*  )}*/}
        {/*</div>*/}
      </div>
    </div>
  );
};

export default DeviceDetails;
