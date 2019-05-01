# Group Project (TODO: Come up with a name)

## Description

This is a website, to be used in waiting rooms, showing various forms of media, information, the latest news, videos, etc, etc. 
The website has an an admin page, allowing authorised people to alter and update content and customise how the slideshow is displayed (e.g. duration, size).

## Development - Zero 2 Hero

Start by cloning this repository locally. Open the project in IntelliJ using `File -> Open -> <This project>`.

### Database - Initial setup

The app requires a MySQL database in order to run locally. 
Make sure you have a local instance of MySQL running, and create an empty schema (with any name you like).

Within IntelliJ, go to `Edit Configurations -> GroupProjectMain -> Configuration -> Environment -> Environment Variables` and add the following:

 - `DB_HOSTNAME` - The address of your local MySQL instance
 - `DB_NAME` - The name of the schema you created
 - `DB_USER` - The user of your local MySQL instance with permission to access the schema
 - `DB_PASS` - The password for the MySQL user
 
Check that everything works by running the app and navigating to `localhost:8080` in a browser.

### Database - Migrations

The schema and initial data for the database are defined using a migration framework called Flyway.

Database migrations are stored in `src/main/resources/db/migration`. 

When the app starts up, Flyway will detect which migrations have been run and which have not yet been run, and it will run the new ones before starting the app.

All schema changes _must_ be done through a database migrations. This means that you don't need to worry about constantly importing structure and data from other team members' databases.

## Development - Process

### Pairing

Feel free to work in pairs whenever you feel like it! Pairing is a great opportunity to learn from each other!

### Feature branches

As this project is a large group effort, we'll use a slightly more structured process when it comes to writing and committing code, to avoid treading on each others' toes.

This repository does not allow commits to be made directly to the `master` branch. Instead, when working on a feature, you should use a feature branch:

```
// Get the latest code...
$ git checkout master
$ git pull

// Start a new feature branch...
$ git checkout -b <branch-name>

// Later, when the code is written
$ git add .
$ git commit -m "Informative message"
$ git push -u origin <branch-name>
```

Then, from GitHub, open a Pull Request for your feature branch. This will let someone review your code, make comments on it, and eventually approve it to be merged into the master branch.

### Trello Board

To keep track of what work is to be done, and who is working on which tasks, there's a [Trello Board](https://trello.com/b/sVGupPnA/group-project) for this project.

Use that board to decide what to work on next! Remember to keep it up to date so that we don't end up with multiple people doing repeat work.

As you progress a task, move it to the right across the board, until it lands in "Done". Try to minimise "half-done" work as much as possible!

