const Sequelize = require('sequelize')

module.exports = {

    getProduct: function(sequelize){

        const Product=sequelize.define("productos",{

            id_Producto:{type: Sequelize.STRING, primaryKey:true},
            nom_Producto:Sequelize,
            feca_Caducidad:Sequelize.DATE,
            fabricante_Prod:Sequelize.STRING,
            price_Producto:Sequelize.INTEGER

        });

        return Product;

    }

};