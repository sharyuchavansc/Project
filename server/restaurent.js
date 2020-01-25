const db = require('./db')
const utils =require('./utils')
const express = require('express')

const router = express.Router()

router.get('/',(request,response)=> {

    const connection = db.connect()
    const statement = `select * from restaurent`
    connection.query(statement,(error,data) => {
        connection.end()
        response.send(utils.createResult(error,data))
        })            
    })

    router.get('/getRestaurent',(request,response)=> {

        const connection = db.connect()
        const statement = `select restaurentId,restaurentName from restaurent`
        connection.query(statement,(error,data) => {
            connection.end()
            response.send(utils.createResult(error,data))
            })            
        })

router.post('/',(request,response) => {
    const {restaurentName,restaurentAddress,restaurentEmailId,restaurentMobileNo} = request.body
    
    const connection = db.connect()
    const statement = `insert into restaurent(restaurentName,restaurentAddress,restaurentEmailId,restaurentMobileNo) values ('${restaurentName}','${restaurentAddress}','${restaurentEmailId}','${restaurentMobileNo}')`

    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

router.delete('/:restaurentId', (request, response) => {
    const {restaurentId} = request.params

    const connection = db.connect()
    const statement = `delete from restaurent where restaurentId = ${restaurentId}`

    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

module.exports = router