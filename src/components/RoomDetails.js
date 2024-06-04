import React, { useEffect, useState } from 'react';
import './RoomDetails.css';

const RoomDetails = ({ room, onBack }) => {
  const [roomDetails, setRoomDetails] = useState(null);

  useEffect(() => {
    const fetchRoomDetails = async () => {
      try {
        const response = await fetch(`http://localhost:8080/rooms/${room.roomId}`);

        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        setRoomDetails(data);
        console.log('Room details fetched successfully:', data);
      } catch (error) {
        console.error('Error fetching room details:', error);
      }
    };

    fetchRoomDetails();
  }, [room.roomId]);

  return (
      <div className="room-details">
        <div className="room-details-header">
          <h2>{roomDetails ? roomDetails.roomName : 'Loading...'}</h2>
          <button className="back-button" onClick={onBack}>
            Back
          </button>
        </div>
        {roomDetails && (
            <div className="room-details-info">
              <p><strong>House Name:</strong> {roomDetails.houseName}</p>
              <p><strong>Room Name:</strong> {roomDetails.roomName}</p>
              <p><strong>Floor:</strong> {roomDetails.floor}</p>
              <p><strong>Height:</strong> {roomDetails.height}m</p>
              <p><strong>Width:</strong> {roomDetails.width}m</p>
              <p><strong>Length:</strong> {roomDetails.length}m</p>
            </div>
        )}
      </div>
  );
};

export default RoomDetails;
