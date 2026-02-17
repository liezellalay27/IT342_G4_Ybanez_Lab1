import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './Dashboard.css';

const Dashboard = () => {
  const navigate = useNavigate();
  const { user } = useAuth();

  return (
    <div className="dashboard-container">
      <div className="dashboard-card">
        <div className="dashboard-header">
          <h2>Dashboard</h2>
        </div>

        <div className="welcome-section">
          <div className="profile-avatar">
            <span>{user?.username?.charAt(0).toUpperCase() || 'U'}</span>
          </div>
          <h3>Welcome back, {user?.username}!</h3>
          <p className="welcome-subtitle">Here's your account overview</p>
        </div>

        <div className="stats-grid">
          <div className="stat-card">
            <div className="stat-icon">✅</div>
            <h4>Account Status</h4>
            <p className="stat-value">Active</p>
          </div>
          
          <div className="stat-card">
            <div className="stat-icon">📅</div>
            <h4>Member Since</h4>
            <p className="stat-value">
              {user?.createdAt 
                ? new Date(user.createdAt).toLocaleDateString('en-US', { 
                    year: 'numeric', 
                    month: 'short' 
                  }) 
                : 'Recently'}
            </p>
          </div>
          
          <div className="stat-card">
            <div className="stat-icon">🆔</div>
            <h4>User ID</h4>
            <p className="stat-value">#{user?.id || 'N/A'}</p>
          </div>
        </div>

        <div className="actions-section">
          <h3>Quick Actions</h3>
          <div className="action-buttons">
            <button className="btn-action" onClick={() => navigate('/profile')}>
              <span className="btn-icon">👤</span>
              View Profile
            </button>
            <button className="btn-action" onClick={() => navigate('/edit-profile')}>
              <span className="btn-icon">✏️</span>
              Edit Profile
            </button>
            <button className="btn-action">
              <span className="btn-icon">🔒</span>
              Change Password
            </button>
            <button className="btn-action">
              <span className="btn-icon">⚙️</span>
              Settings
            </button>
          </div>
        </div>

        <div className="recent-activity">
          <h3>Recent Activity</h3>
          <div className="activity-list">
            <div className="activity-item">
              <div className="activity-icon">🔐</div>
              <div className="activity-content">
                <p className="activity-title">Login Successful</p>
                <p className="activity-time">Today</p>
              </div>
            </div>
            <div className="activity-item">
              <div className="activity-icon">✨</div>
              <div className="activity-content">
                <p className="activity-title">Welcome to Dashboard</p>
                <p className="activity-time">Now</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
