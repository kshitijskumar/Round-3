const mongoose = require('mongoose');

// HOW DATA IS FORMATTED IN MONGODB
const postSchema = mongoose.Schema({
    Age18To24 : { 
                type : Number,
                required : true
            },
    Age24To30 : { 
                type : Number,
                required : true
            },
    Age30plus : { 
                type : Number,
                required : true
            },
    AgeUnder18 : { 
                type : Number,
                required : true
            },
     gender0 : { 
                type : Number,
                required : true
            },
     gender1 : { 
                type : Number,
                required : true
            },
      gender2 : { 
                type : Number,
                required : true
            },
      tier1 : { 
                type : Number,
                required : true
            },
       tier2 : { 
                type : Number,
                required : true
            },
       tier3 : { 
                type : Number,
                required : true
            },
       userId : { 
                type : Number,
                required : true
            },
       trailId : { 
                type : Number,
                required : true
            },
       event_type : { 
                type : String,
                required : true
            },
       
    url : {
                type : String,
                required : true
    }      
})

module.exports = mongoose.model('Posts',postSchema);