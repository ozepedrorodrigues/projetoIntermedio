import React, { useState } from 'react';
import './App.css';
import SunInfoCard from './components/SunInfoCard';
import TemperatureCard from './components/TemperatureCard';
import RoomList from './components/RoomList';
import RoomDetails from './components/RoomDetails';

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
        { name: 'Lamp' },
        { name: 'TV' },
        { name: 'Air Conditioner' }
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
        { name: 'Lamp' },
        { name: 'Fan' },
        { name: 'Heater' }
      ]
    }
    // Adicione mais quartos conforme necessário
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
        <h1>SmartHome System</h1>
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
