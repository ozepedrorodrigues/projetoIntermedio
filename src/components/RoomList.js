import React from 'react';
import './RoomList.css';

const RoomList = ({ rooms, onRoomSelect }) => {
  return (
    <div className="room-list-container">
      <div className="room-list">
        {rooms.map((room) => (
          <div key={room} className="room-item">
            <span>{room.roomId}</span>
            <button className="view-more-button" onClick={() => onRoomSelect(room)}>View More</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default RoomList;
