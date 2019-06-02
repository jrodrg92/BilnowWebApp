const ctrlUser = require('./ctrlUser');

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
        pet.findAll({model: this.pet, where:{id_Duenio:userl.id_Usuario}}).then(pets=>{
            ctrlUser.setPets(pets);
            res.render('user', {pets,nom_Usuario:userl.nom_Usuario,
                                                  ap_Usuario:userl.ap_Usuario, 
                                                  tlf_Usuario:userl.tlf_Usuario, 
                                                 dir_Usuario:userl.dir_Usuario, 
                                                 email_Usuario:userl.email_Usuario});
    
        });
    });
    
};
