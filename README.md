# Android Assignment

A robust Android application that provides a seamless web browsing experience, saves browsing history locally using Room Database, and synchronizes data with a remote API via Retrofit.

## 🚀 Key Features Implemented
- **WebView Integration:** Smart navigation with Back and Close behavior.
- **Local Persistence:** URL history with exact timestamps saved using **Room Database**.
- **Remote Sync:** Background data upload to **Beeceptor API** using Retrofit.
- **Architecture:** Built on **MVVM Pattern** for clean code and scalability.
- **Async Operations:** Used **Kotlin Coroutines** for smooth UI performance (no blocking).
- **Reactive UI:** **LiveData** and **ViewBinding** for real-time data updates.

## 🛠️ Tech Stack
- **Language:** Kotlin
- **Database:** Room (SQLite)
- **Networking:** Retrofit & OkHttp
- **Architecture:** MVVM
- **UI Components:** RecyclerView, Material Design, ViewBinding

## ✅ Assignment Status
- [x] Home Screen UI & Navigation
- [x] WebView Screen with Back & Close behavior
- [x] URL History saved with timestamps (Room DB)
- [x] RecyclerView for History Screen
- [x] Invalid URL format edge case handling
- [x] Upload history to API (Beeceptor)
- [x] Code Optimization & Repository Pattern

## 📸 Screenshots
<p align="left">
  <img src="https://github.com/user-attachments/assets/684d8115-a05d-4b9a-8674-bb320d451b9e" width="220"/>  
  <img src="https://github.com/user-attachments/assets/b2ca4d88-ff16-42c6-8d01-ebb9c113c939" width="220"/>
  <img src="https://github.com/user-attachments/assets/904e217b-23bb-4d14-b287-e1d2a8ad982e" width="220"/>
</p>

## ⚙️ How to Run
1. Clone the repository.
2. Open in Android Studio.
3. Sync Gradle and run the app.
4. Set your Beeceptor URL in `Constants.kt` if needed.
