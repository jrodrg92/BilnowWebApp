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

module.exports.modUser = function(req,res,user,userBD,pet){

    var nom,ap,tlf,dir,email,psswd;

    if(req.body.nom_Usuario==null){
        nom =user.nom_Usuario;
    }
    else{
        nom=req.body.nom_Usuario;
    }
    if(req.body.ap_Usuario==null){
        ap =user.ap_Usuario;
    }
    else{
        ap=req.body.ap_Usuario;
    }
    if(req.body.tlf_Usuario==null){
        tlf =user.tlf_Usuario;
    }
    else{
        tlf=req.body.tlf_Usuario;
    }
    if(req.body.dir_Usuario==null){
        dir =user.dir_Usuario;
    }
    else{
        dir=req.body.dir_Usuario;
    }
    if(req.body.email_Usuario==null){
        email =user.email_Usuario;
    }
    else{
        email=req.body.email_Usuario;
    }
    if(req.body.pswd_Usuario==null){
        psswd =user.pswd_Usuario;
    }
    else{
        psswd=req.body.pswd_Usuario;
    }

    userBD.update({
        nom_Usuario:nom,
        ap_Usuario:ap,
        tlf_Usuario:tlf,
        dir_Usuario:dir,
        email_Usuario:email,
        pswd_Usuario:psswd},{where:{id_Usuario:user.id_Usuario}}).then(
        userl=>{
            
        }
        );

};
