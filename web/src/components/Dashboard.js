import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './Dashboard.css';

const Dashboard = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-card">
        <div className="dashboard-header">
          <h2>Dashboard</h2>
          <button onClick={handleLogout} className="btn-logout">
            Logout
          </button>
        </div>

        <div className="profile-section">
          <div className="profile-avatar">
            <span>{user?.username?.charAt(0).toUpperCase() || 'U'}</span>
          </div>
          
          <div className="profile-info">
            <h3>Welcome, {user?.username}!</h3>
            <p className="user-email">{user?.email}</p>
          </div>
        </div>

        <div className="stats-grid">
          <div className="stat-card">
            <h4>Account Status</h4>
            <p className="stat-value">Active</p>
          </div>
          
          <div className="stat-card">
            <h4>Member Since</h4>
            <p className="stat-value">
              {user?.createdAt 
                ? new Date(user.createdAt).toLocaleDateString() 
                : 'Recently'}
            </p>
          </div>
          
          <div className="stat-card">
            <h4>User ID</h4>
            <p className="stat-value">#{user?.id || 'N/A'}</p>
          </div>
        </div>

        <div className="actions-section">
          <h3>Quick Actions</h3>
          <div className="action-buttons">
            <button className="btn-action" onClick={() => navigate('/edit-profile')}>Edit Profile</button>
            <button className="btn-action">Change Password</button>
            <button className="btn-action">Account Settings</button>
          </div>
        </div>

        <div className="info-section">
          <h3>Account Information</h3>
          <div className="info-grid">
            <div className="info-item">
              <span className="info-label">Username:</span>
              <span className="info-value">{user?.username}</span>
            </div>
            <div className="info-item">
              <span className="info-label">Email:</span>
              <span className="info-value">{user?.email}</span>
            </div>
            <div className="info-item">
              <span className="info-label">Role:</span>
              <span className="info-value">{user?.role || 'User'}</span>
            </div>
            <div className="info-item">
              <span className="info-label">Status:</span>
              <span className="info-value status-active">Active</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
