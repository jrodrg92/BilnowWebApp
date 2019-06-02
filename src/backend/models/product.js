const Sequelize = require('sequelize');
const faker=require('faker');

module.exports = {

    getProduct: function(sequelize, user){

        this.user=user;

        const Product=sequelize.define("Productos",{

            id_Producto:{type: Sequelize.STRING, primaryKey:true},
            nom_Producto:Sequelize.STRING,
            foto_Producto:Sequelize.STRING,
            fabricante_Pro:Sequelize.STRING,
            price_Producto:Sequelize.INTEGER

        });

        Product.hasMany(Product, {as: 'Products'});

        return Product;

    }

};