const mongoose = require('mongoose');

// HOW DATA IS FORMATTED IN MONGODB
const postSchema = mongoose.Schema({
    creatorName : { 
                type : String,
                required : true
            },
    vlogId : { 
                type : String,
                required : true
            },
    creatorId : { 
                type : String,
                required : true
            },
    AgeUnder18 : { 
                type : Number,
                required : false
            },
     gender0 : { 
                type : Number,
                required : false
            },
     gender1 : { 
                type : Number,
                required : false
            },
      gender2 : { 
                type : Number,
                required : false
            },
      tier1 : { 
                type : Number,
                required : false
            },
       tier2 : { 
                type : Number,
                required : false
            },
       tier3 : { 
                type : Number,
                required : false
            },
       userId : { 
                type : Number,
                required : false
            },
       trailId : { 
                type : Number,
                required : false
            },
       event_type : { 
                type : String,
                required : false
            },
       
    vlogurl : {
                type : String,
                required : true
    }      
})

module.exports = mongoose.model('Posts',postSchema);