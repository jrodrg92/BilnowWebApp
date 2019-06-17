const ctrlUser = require('./ctrlUser');

module.exports.showlogin = function(res){

    res.render('login');

};

module.exports.login = function(req,res,user,pet){

    var idUser=req.body.id_Usuario;
    var psswd=req.body.pswd_Usuario;

    user.findOne({model:this.user, where:{id_Usuario: idUser, pswd_Usuario: psswd}}).then(usuario =>{

        if(usuario==null){
            console.log("vacio");
        }
        else{
            ctrlUser.showUser(res,usuario,pet);
        }
  
    })

};
