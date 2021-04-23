const express = require('express');
const router = express.Router();
const Post = require('../models/post');


//SUBMIT THE POST
router.post('/', async(req,res) =>{
    // console.log(req.body,"hello");

    const post = new Post({
	Age18To24: req.body.Age18To24,
   	Age24To30: req.body.Age24To30,
   	Age30plus: req.body.Age30plus,
   	AgeUnder18: req.body.AgeUnder18,
   	event_type: req.body.event_type,
   	gender0: req.body.gender0,
   	gender1: req.body.gender1,
   	gender2: req.body.gender2,
   	tier1: req.body.tier1,
   	tier2: req.body.tier2,
   	tier3: req.body.tier3,
   	userId: req.body.userId,
   	trailId: req.body.trailId,
   	url: req.body.url
        
    }); 

    // console.log(post);
    try{
        const savedPost = await post.save();
        res.json(savedPost); // RESPONSE FOR CONSOLE
    }catch(err){ 
        res.json({message: err}); // RESPONSE FOR CONSOLE
    }


// without async
    // post.save()
    //     .then(data => {
    //         res.json(data);
    //     })
    //     .catch(err => {
    //         res.json({
    //             message : err
    //         });
    //     })

} )

// GET POST 
router.get('/',async(req,res) => {
    try{
        const posts = await Post.find();
        res.json(posts); // RESPONSE FOR CONSOLE
    }catch(err){
        res.json({ message : err }); // RESPONSE FOR CONSOLE
    }
})


// FIND SPECIFIC POST
router.get('/:postId',async(req,res) => {
    try{
    const post = await Post.findById(req.params.postId);
    res.json(post);
    }catch(err){
        res.json({message : err});
    }
})


//DELETE POST
router.delete('/:postId', async(req,res) => {
    try{
        const deletedPost = await Post.deleteOne({ _id : req.params.postId});
        res.json(deletedPost);
    }catch(err){
        res.json({message : err});
    }
})


//UPDATE POST
router.patch('/:postId', async(req,res) => {
    try{
        const updatedPost = await Post.updateOne(
            { _id : req.params.postId},
            { $set :{title : req.body.title}}
        );
        res.json(updatedPost);
    }catch(err){
        res.json({message : err});
    }
})


module.exports = router;
