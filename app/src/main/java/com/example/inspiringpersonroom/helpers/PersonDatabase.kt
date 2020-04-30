package com.example.inspiringpersonroom.helpers

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.inspiringpersonroom.DAO.PersonDao
import com.example.inspiringpersonroom.DAO.QuoteDao
import com.example.inspiringpersonroom.Entity.Person
import com.example.inspiringpersonroom.Entity.Quote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(version = 2, entities = arrayOf(
    Person::class,
    Quote::class))
abstract  class PersonDatabase : RoomDatabase(){
    abstract fun personDao() : PersonDao
    abstract fun quoteDao(): QuoteDao

    companion object {
        private val preferenceManager =
            PreferenceManager()
        private const val NAME = "person_database"
        private var INSTANCE: PersonDatabase? = null

        fun getInstance(scope: CoroutineScope): PersonDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    InspiringPersonApp.ApplicationContext,
                    PersonDatabase::class.java,
                    NAME
                )
                    .allowMainThreadQueries()
                    .addCallback(
                        PersonDatabaseCallback(
                            scope
                        )
                    )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as PersonDatabase
        }

        private  class PersonDatabaseCallback(
            private val scope:CoroutineScope
        ) : RoomDatabase.Callback(){

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                if(preferenceManager.isFirstTime()){
                    INSTANCE?.let{
                        database -> scope.launch {
                            populateDatabase(database.personDao(), database.quoteDao())
                            preferenceManager.saveFirstTime()
                        }
                    }
                }
            }
            suspend fun populateDatabase(personDao: PersonDao, quoteDao: QuoteDao){
                personDao.deleteAll()
                var person = Person(
                    0,
                    "Nikola Tesla",
                    "1.1.1997.",
                    "Born and raised in the Austrian Empire, Tesla studied engineering and physics in the 1870s without receiving a degree, and gained practical experience in the early 1880s working in telephony and at Continental Edison in the new electric power industry.",
                    "https://www.biography.com/.image/t_share/MTY2Njc5MTIyNzY2OTk2NTM1/nikola_tesla_napoleon-sarony-public-domain-via-wikimedia-commons.jpg"
                )
                personDao.insert(person)
                person = Person(
                    0,
                    "Dennis Ritchie",
                    "9.9.1941",
                    "Ritchie is best known as the creator of the C programming language, a key developer of the Unix operating system, and co-author of the book The C Programming Language; he was the 'R' in K&R (a common reference to the book's authors Kernighan and Ritchie). Ritchie worked together with Ken Thompson, who is credited with writing the original version of Unix.",
                    "https://www.invent.org/sites/default/files/styles/inductee_detail_media/public/inductees/2019-02/Ritchie%2C-Dennis_b%26w.jpg?h=157d851b&itok=HAZRfT8c"
                )
                personDao.insert(person)
                person = Person(
                    0,
                    "Steve Jobs",
                    "24.2.1955",
                    "Jobs was born in San Francisco, California, and put up for adoption. He was raised in the San Francisco Bay Area. He attended Reed College in 1972 before dropping out that same year, and traveled through India in 1974 seeking enlightenment and studying Zen Buddhism.",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg/220px-Steve_Jobs_Headshot_2010-CROP_%28cropped_2%29.jpg"
                )
                personDao.insert(person)
                person = personDao.getByName("Nikola Tesla")
                var quote = Quote(
                    0,
                    person.id,
                    "I don’t care that they stole my idea …  I care that they don’t have any of their own."
                )
                quoteDao.insert(quote)
                quote = Quote(
                    0,
                    person.id,
                    "I do not think you can name many great inventions that have been made by married men."
                )
                quoteDao.insert(quote)
                person = personDao.getByName("Dennis Ritchie")
                quote = Quote(
                    0,
                    person.id,
                    "UNIX is basically a simple operating system, but you have to be a genius to understand the simplicity."
                )
                quoteDao.insert(quote)
                quote = Quote(
                    0,
                    person.id,
                    "The only way to learn a new programming language is by writing programs in it."
                )
                quoteDao.insert(quote)
                person = personDao.getByName("Steve Jobs")
                quote = Quote(
                    0,
                    person.id,
                    "Creativity is just connecting things."
                )
                quoteDao.insert(quote)
                quote = Quote(
                    0,
                    person.id,
                    "Don’t let the noise of others’ opinions drown out your own inner voice."
                )
                quoteDao.insert(quote)
            }
        }
    }
}