import React from 'react';

const RoomsButton = ({ onClick }) => {
    return (
        <button className="rooms-button" onClick={onClick}>
            Rooms
        </button>
    );
};

export default RoomsButton;