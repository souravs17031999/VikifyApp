# VikifyApp

The mainActivity Leads to an interface which the user can use to capture a video, Upload it to the server and review it if needed.

The backend servers and storage being used are the Firebase Storage and realtime database.

Everytime the user exits the app the previous count of the video is kept intact, so that the user can continue populating the DB without any indexing glitches.

The Onclick event in recrded video section helps in replaying the respective videos.

The app is preloaded with 6 videos which I captured during test phasr. i apologize for the video quality as my phone has a bad camera lens. 

The video in Video Recorded sectuon are displayed in a recycler view and hence will get populated in real time.
