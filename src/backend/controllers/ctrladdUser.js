const ctrlUser = require('./ctrlUser');
const ctrladdPetWin= require('./ctrlAddPetWin');


module.exports.showaddUser = function(rol,res){

    res.render('addUser', {rol});

};

module.exports.showmodUser = function(req,res,user){

    res.render('modUser', {nom_Usuario:user.dataValues.nom_Usuario,
                            ap_Usuario:user.ap_Usuario, 
                            tlf_Usuario:user.tlf_Usuario, 
                            dir_Usuario:user.dir_Usuario, 
                            email_Usuario:user.email_Usuario})

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

module.exports.modUser = function(req,res,user,pet){

    user.update({ id_Usuario:req.body.id_Usuario,
        nom_Usuario:req.body.nom_Usuario,
        ap_Usuario:req.body.ap_Usuario,
        tlf_Usuario:req.body.tlf_Usuario,
        dir_Usuario:req.body.dir_Usuario,
        email_Usuario:req.body.email_Usuario,
        pswd_Usuario:req.body.pswd_Usuario},{where:{id_Usuario:req.body.id_Usuario}}).then(
        userl=>{
            
        }
        );

};
