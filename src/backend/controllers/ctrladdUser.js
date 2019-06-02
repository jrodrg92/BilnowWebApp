const ctrlUser = require('./ctrlUser');
const ctrladdPetWin= require('./ctrlAddPetWin');


module.exports.showaddUser = function(res){

    res.render('addUser');

};


module.exports.addUser = function(req,res,user,pet){


    user.create({id_Usuario:req.body.id_Usuario,
        nom_Usuario:req.body.nom_Usuario,
        ap_Usuario:req.body.ap_Usuario,
        tlf_Usuario:req.body.tlf_Usuario,
        dir_Usuario:req.body.dir_Usuario,
        email_Usuario:req.body.email_Usuario,
        pswd_Usuario:req.body.pswd_Usuario
    }).then(userl=>{

        ctrlUser.showUser(res,userl,pet);

    });
    
};
