import React from 'react';
import './RoomDetails.css';

const RoomDetails = ({room, onBack}) => {

    return (
        <div className="room-details">
            <div className="room-details-header">
                <h2>{room ? room.roomName : 'Loading...'}</h2>
                <button className="back-button" onClick={onBack}>
                    Back
                </button>
            </div>
            {room && (
                <div className="room-details-info">
                    <p><strong>Floor:</strong> {room.floor}</p>
                    <p><strong>Height:</strong> {room.height}m</p>
                    <p><strong>Width:</strong> {room.width}m</p>
                    <p><strong>Length:</strong> {room.length}m</p>
                </div>
            )}
        </div>
    );
};

export default RoomDetails;
