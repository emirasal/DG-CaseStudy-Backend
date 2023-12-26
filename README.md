# DreamGames-CaseStudy (Backend)
I used Apache Maven for this case study alongside MySQL. 
Organized the solution as: Controllers, Services, Repositories and Models.
lombok builder is used for models.
Used "Scheduled cron" to manage the tournament opening and closing times.

When player enters a tournament. They're added to a queue and start searching for other people from diffirent countries. To check if they've found a group, a loop is running every 2 seconds and looking at the database (When there is not enough people response may wait several minutes).

I've created 5 database tables:
### User:
+ userID (primary key)
+ username
+ level
+ coins
+ pendingCoins
+ country (enum)

### Tournament:
+ tournamentID (primary key)
+ tournamentState (enum)

### TournamentGroup:
+ groupID (primary key)
+ tournament (foreign key)

### TournamentUserScore
+ tournamentUserID (primary key)
+ user (foreign key)
+ tournamentGroup (foreign key)
+ score

### TournamentCountryScore
+ tournamentCountryID
+ tournament (foreign key)
+ country (enum)
+ score

 
![image]()
