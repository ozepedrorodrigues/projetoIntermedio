import React, { useState } from 'react';
import './App.css';
import SunInfoCard from './components/SunInfoCard';
import TemperatureCard from './components/TemperatureCard';
import RoomList from './components/RoomList';
import RoomDetails from './components/RoomDetails';
import { type } from '@testing-library/user-event/dist/type';

const App = () => {
  const [showRooms, setShowRooms] = useState(false);
  const [selectedRoom, setSelectedRoom] = useState(null);

  const rooms = [
    {
      roomID: '1',
      name: 'Living Room',
      length: 5,
      height: 2.5,
      width: 4,
      floor: 1,
      devices: [
        {
          deviceID: '1',
          name: 'Lamp',
          type: 'Light',
          status: 'active',
          room: 'Living Room',
          sensors: [
            { id: '1', model: 'SensorModel1', type: 'LightSensor' }
          ],
          actuators: [
            { id: '1', model: 'ActuatorModel1', type: 'LightActuator' }
          ]
        },
        {
          deviceID: '2',
          name: 'TV',
          type: 'Entertainment',
          status: 'active',
          room: 'Living Room',
          sensors: [
            { id: '2', model: 'SensorModel2', type: 'ImageSensor' }
          ],
          actuators: [
            { id: '2', model: 'ActuatorModel2', type: 'ImageActuator' }
          ]
        },
        {
          deviceID: '3',
          name: 'Air Conditioner',
          type: 'Climate Control',
          status: 'active',
          room: 'Living Room',
          sensors: [
            { id: '3', model: 'SensorModel3', type: 'TemperatureSensor' }
          ],
          actuators: [
            { id: '3', model: 'ActuatorModel3', type: 'TemperatureActuator' }
          ]
        }
      ]
    },
    {
      roomID: '2',
      name: 'Bedroom',
      length: 4,
      height: 2.5,
      width: 3,
      floor: 2,
      devices: [
        {
          deviceID: '4',
          name: 'Lamp',
          type: 'Light',
          status: 'active',
          room: 'Bedroom',
          sensors: [
            { id: '4', model: 'SensorModel4', type: 'LightSensor' }
          ],
          actuators: [
            { id: '4', model: 'ActuatorModel4', type: 'LightActuator' }
          ]
        },
        {
          deviceID: '5',
          name: 'Fan',
          type: 'Climate Control',
          status: 'active',
          room: 'Bedroom',
          sensors: [
            { id: '5', model: 'SensorModel5', type: 'AirFlowSensor' }
          ],
          actuators: [
            { id: '5', model: 'ActuatorModel5', type: 'AirFlowActuator' }
          ]
        },
        {
          deviceID: '6',
          name: 'Heater',
          type: 'Climate Control',
          status: 'active',
          room: 'Bedroom',
          sensors: [
            { id: '6', model: 'SensorModel6', type: 'TemperatureSensor' }
          ],
          actuators: [
            { id: '6', model: 'ActuatorModel6', type: 'TemperatureActuator' }
          ]
        }
      ]
    }
  ];

  const handleRoomsClick = () => {
    setShowRooms(true);
  };

  const handleBackClick = () => {
    if (selectedRoom) {
      setSelectedRoom(null);
    } else {
      setShowRooms(false);
    }
  };

  const handleRoomSelect = (room) => {
    setSelectedRoom(room);
  };

  return (
    <div className="home-container">
      <header className="header">
        <h1>SmartHome</h1>
      </header>
      <div className="cards-container">
        {showRooms ? (
          selectedRoom ? (
            <RoomDetails room={selectedRoom} onBack={handleBackClick} />
          ) : (
            <div className="room-view">
              <div className="room-header">
                <h2>Rooms</h2>
                <button className="back-button" onClick={handleBackClick}>
                  Back
                </button>
              </div>
              <RoomList rooms={rooms} onRoomSelect={handleRoomSelect} />
            </div>
          )
        ) : (
          <>
            <div className="left-card main-card">
              <button className="rooms-button" onClick={handleRoomsClick}>
                Rooms
              </button>
            </div>
            <div className="right-cards">
              <SunInfoCard type="sunrise" />
              <SunInfoCard type="sunset" />
              <TemperatureCard />
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default App;
