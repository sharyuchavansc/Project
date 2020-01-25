const express = require('express')
const bodyParser = require('body-parser')

const routerUser = require('./user')
const routerRestaurent = require('./restaurent')
const routerDish = require('./dish')

const app = express()

// for CORS
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*"); // update to match the domain you will make the request from
    res.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE"); // update to match the domain you will make the request from
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});


app.use(bodyParser.json())
app.use(express.static('dishImages'))
app.use('/user',routerUser)
app.use('/restaurent',routerRestaurent)
app.use('/dish',routerDish)

app.listen(5000, '0.0.0.0', () => {
    //console.log('------------------------------------')
    console.log('server started  on port 5000')
    //console.log('Server started Successfully...!!!')
    //console.log('-------------------------------------')
})