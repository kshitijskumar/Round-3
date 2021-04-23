const express = require('express');
const app = express();
const mongoose = require('mongoose');
require('dotenv/config')

const postsRoute = require('./routes/posts');



app.use(express.json());

//Middleware
// app.use('/post' , () =>{
//     console.log("this is middleware");
// })

//Post Route
app.use('/posts' , postsRoute );


app.get('/' , (req,res) =>{
    res.send("home home");
});


mongoose.connect( "mongodb+srv://trell:qwerty1234@cluster0.scg4o.mongodb.net/trell?retryWrites=true&w=majority",{ useNewUrlParser: true , useUnifiedTopology: true} , () => console.log("connected to DB"));
app.listen(5000);