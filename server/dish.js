const db = require('./db')
const utils =require('./utils')
const express = require('express')
const multer = require('multer')
const upload = multer({dest: './dishImages/'})

const router = express.Router()

router.get('/',(request,response)=> {

    const connection = db.connect()
    const statement = `select * from dish`
    connection.query(statement,(error,data) => {
        connection.end()
        response.send(utils.createResult(error,data))
        })            
    })


    router.get('/', (request, response) => {
        
        const connection = db.connect()
        const statement = `select * from dish`
        connection.query(statement, (error, dishes) => {
            connection.end()
            response.send(utils.createResult(error, dishes))
        })
    })

    router.get('/allDish', (request, response) => {
        
        const connection = db.connect()
        const statement = `select * from dish`
        connection.query(statement, (error, dishes) => {
            connection.end()
            response.send(utils.createResult(error, dishes))
        })
    })

    router.get('/my/:dishId', (request, response) => {
        const {dishId} = request.params
        const connection = db.connect()
        const statement = `select * from dish where dishId = ${dishId}`
        connection.query(statement, (error, dishes) => {
            connection.end()
            response.send(utils.createResult(error, dishes))
        })
    })

router.post('/',upload.single('dishImage'),(request,response) => {
    const {dishName,dishPrice,dishDescription,restaurentId} = request.body
    const dishImage = request.file.filename
    
    const connection = db.connect()
    const statement = `insert into dish(dishName,dishPrice,dishDescription,dishImage,restaurentId) values ('${dishName}','${dishPrice}','${dishDescription}','${dishImage}','${restaurentId}')`

    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

router.delete('/:dishId', (request, response) => {
    const {dishId} = request.params

    const connection = db.connect()
    const statement = `delete from dish where dishId = ${dishId}`

    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})


module.exports = router

