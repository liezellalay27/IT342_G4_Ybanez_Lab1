import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './Profile.css';

const Profile = () => {
  const navigate = useNavigate();
  const { user } = useAuth();

  const getInitials = () => {
    if (user?.fullName) {
      return user.fullName.split(' ').map(n => n[0]).join('').toUpperCase().slice(0, 2);
    }
    return user?.username?.slice(0, 2).toUpperCase() || 'U';
  };

  return (
    <div className="profile-container">
      <div className="profile-card">
        <div className="profile-header-section">
          <div className="profile-avatar-large">
            <span>{getInitials()}</span>
          </div>
          <h2>{user?.fullName || user?.username}</h2>
          <p className="profile-subtitle">@{user?.username}</p>
        </div>

        <div className="profile-details">
          <h3>Personal Information</h3>
          <div className="profile-info-grid">
            <div className="profile-info-item">
              <label>Full Name</label>
              <p>{user?.fullName || 'Not provided'}</p>
            </div>
            
            <div className="profile-info-item">
              <label>Username</label>
              <p>{user?.username}</p>
            </div>
            
            <div className="profile-info-item">
              <label>Email Address</label>
              <p>{user?.email}</p>
            </div>
            
            <div className="profile-info-item">
              <label>Phone Number</label>
              <p>{user?.phoneNumber || 'Not provided'}</p>
            </div>
            
            <div className="profile-info-item">
              <label>Role</label>
              <p className="role-badge">{user?.role || 'User'}</p>
            </div>
            
            <div className="profile-info-item">
              <label>Account Status</label>
              <p className="status-badge active">Active</p>
            </div>
          </div>
        </div>

        <div className="profile-meta">
          <h3>Account Details</h3>
          <div className="meta-grid">
            <div className="meta-item">
              <span className="meta-label">User ID:</span>
              <span className="meta-value">#{user?.id || 'N/A'}</span>
            </div>
            <div className="meta-item">
              <span className="meta-label">Member Since:</span>
              <span className="meta-value">
                {user?.createdAt 
                  ? new Date(user.createdAt).toLocaleDateString('en-US', { 
                      year: 'numeric', 
                      month: 'long', 
                      day: 'numeric' 
                    }) 
                  : 'Recently'}
              </span>
            </div>
            <div className="meta-item">
              <span className="meta-label">Last Updated:</span>
              <span className="meta-value">
                {user?.updatedAt 
                  ? new Date(user.updatedAt).toLocaleDateString('en-US', { 
                      year: 'numeric', 
                      month: 'long', 
                      day: 'numeric' 
                    }) 
                  : 'N/A'}
              </span>
            </div>
          </div>
        </div>

        <div className="profile-actions">
          <button 
            className="btn-edit-profile" 
            onClick={() => navigate('/edit-profile')}
          >
            Edit Profile
          </button>
          <button 
            className="btn-back" 
            onClick={() => navigate('/dashboard')}
          >
            Back to Dashboard
          </button>
        </div>
      </div>
    </div>
  );
};

export default Profile;
