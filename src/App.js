import React, { useEffect, useState } from 'react';
import './App.css';
import SunInfoCard from './components/SunInfoCard';
import TemperatureCard from './components/TemperatureCard';
import RoomList from './components/RoomList';
import RoomDetails from './components/RoomDetails';

const App = () => {
  const [showRooms, setShowRooms] = useState(false);
  const [selectedRoom, setSelectedRoom] = useState(null);
  const [rooms, setRooms] = useState([]);

  useEffect(() => {
    const fetchRooms = async () => {
      try {
        const response = await fetch('http://localhost:8080/rooms');

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();

        if (data._embedded && Array.isArray(data._embedded.rooms) && data._embedded.rooms.every(room => room.hasOwnProperty('roomId'))) {
          setRooms(data._embedded.rooms);
          console.log('Rooms fetched successfully:', data._embedded.rooms);
        } else {
          console.error('Failed to fetch rooms: Invalid data format', data);
        }
      } catch (error) {
        console.error('Error fetching rooms:', error);
      }
    };

    fetchRooms();
  }, []); // Empty dependency array ensures this runs only once when the component mounts

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