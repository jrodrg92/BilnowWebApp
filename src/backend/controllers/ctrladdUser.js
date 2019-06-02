var ctrlUser = require('./ctrlUser');

module.exports.showaddUser = function(res){

    res.render('addUser');

};


module.exports.addUser = function(req,res,user,pet){

    res.render('addUser');

    user.create(req.body)
    .then(useradded=>{

        ctrlUser.showUser(res,useradded,pet);

    });


};
