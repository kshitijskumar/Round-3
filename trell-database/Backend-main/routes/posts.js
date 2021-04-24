const express = require('express');
const router = express.Router();
const Post = require('../models/post');


//SUBMIT THE POST
router.post('/', async(req,res) =>{
    // console.log(req.body,"hello");

    const post = new Post({
	creatorName: req.body.creatorName,
   	vlogId: req.body.vlogId,
   	creatorId: req.body.creatorId,
   	vlogurl: req.body.vlogurl
        
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
