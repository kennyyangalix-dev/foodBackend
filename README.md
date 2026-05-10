Restaurant Finder API

A Spring Boot backend that intelligently filters Google Maps Places data to find the best dining options. This API goes beyond a simple search by enforcing strict quality and availability standards.

Features:
- Takes a string query and fetches live data from the Google Place API
- Caches Google Place API calls based on query
- Returns filtered restaurants (3.5+ rating, minimum 25 user ratings, currently open)

Tech Stack:
- Kotlin, Spring Boot

Setup:
- Clone repository
- Set environment object google.api.key= GOOGLE_API_KEY
- Run via IntelliJ

Endpoints:
- POST /getPlaces
