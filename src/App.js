import React, { useEffect, useState } from 'react';
import './App.css';
import SunInfoCard from './components/SunInfoCard';
import TemperatureCard from './components/TemperatureCard';
import RoomDetails from './components/RoomDetails';
import RoomView from './components/RoomView';
import RoomsButton from './components/RoomsButton';
import {Header} from "./components/Header";

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
          const rooms = data._embedded.rooms;
          console.log('Rooms fetched successfully:', rooms);

          // Fetch details for each room
          const detailedRooms = await Promise.all(rooms.map(async (room) => {
            const roomDetailsResponse = await fetch(`http://localhost:8080/rooms/${room.roomId}`);

            if (!roomDetailsResponse.ok) {
              throw new Error(`HTTP error! status: ${roomDetailsResponse.status}`);
            }

            const roomDetailsData = await roomDetailsResponse.json();
            return { ...room, ...roomDetailsData }; // Merge room with its details
          }));

          setRooms(detailedRooms);
          console.log('Detailed rooms fetched successfully:', detailedRooms);
        } else {
          console.error('Failed to fetch rooms: Invalid data format', data);
        }
      } catch (error) {
        console.error('Error fetching rooms:', error);
      }
    };

    fetchRooms();
  }, []);

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
        <Header />
        <div className="cards-container">
          {showRooms ? (
              selectedRoom ? (
                  <RoomDetails room={selectedRoom} onBack={handleBackClick} />
              ) : (
                  <RoomView rooms={rooms} onRoomSelect={handleRoomSelect} onBack={handleBackClick} />
              )
          ) : (
              <>
                <div className="left-card main-card">
                  <RoomsButton onClick={handleRoomsClick} />
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
