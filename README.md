# WheelsShop
✅ List of Completed Tasks
  ✅ Project onboarding: familiarized with the backend architecture and project structure.
  
  ✅ Removed legacy integrations: excluded deprecated services such as VK and Yandex from the project.
  
  ✅ Secured sensitive data: extracted credentials and API keys into an external properties file not committed to version control.
  
  ✅ Filesystem refactoring: modernized the file upload logic in FileUtil#upload to use Files and Path APIs for better performance and maintainability.
  
  ✅ Feature: task tagging: implemented tagging functionality for tasks, including REST API endpoints and service layer logic.
  
  ✅ Feature: time tracking: added logic to track and calculate the time a task spends in "In Progress" and "Testing" states.
  
  ✅ Dockerization: created a Dockerfile for the backend service to simplify deployment.
  
  ✅ Docker Compose: built a docker-compose.yml configuration to run the application alongside the PostgreSQL database and NGINX reverse proxy.
  
  ✅ Redis integration: configured Redis caching for frequently accessed entities (e.g., car listings, client profiles) to improve performance.
