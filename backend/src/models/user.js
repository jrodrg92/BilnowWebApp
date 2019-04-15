const Sequelize = require('sequelize');

module.exports = {

    getUser: function(sequelize){
  
        const User= sequelize.define("usuarios",{
            id_Usuario: {type: Sequelize.STRING, primaryKey: true},
            nom_Usuario: Sequelize.STRING,
            ap_Usuario: Sequelize.STRING,
            tlf_Usuario: Sequelize.STRING,
            dir_Usuario: Sequelize.STRING,
            pswd_Usuario: Sequelize.STRING,
            email_Usuaio:Sequelize.STRING
            },
            {  
                freezeTableName: true 
            });

            return User;

    }
    
};