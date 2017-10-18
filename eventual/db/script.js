use celebrities

db.dropDatabase()

db.createCollection('celebrities')

db.celebrities.insert({
	name: {first: 'Thom', last: 'CS'}
})
