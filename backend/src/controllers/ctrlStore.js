const ctrlUser= require('./ctrlUser');
var express=require('express');
class CtrlStore{

    constructor(route, dir, user, res, prod, req, pet){

        this.dir=dir;
        this.User=user;
        this.prod=prod;
        this.pet=pet;
        const prods = [];

        this.prod.findAll({model:this.prod}).then(prods => { 
                res.render(dir + '/views/store', {prods});
        }).catch(error => {
            console.log(error);
        });

    }

}

module.exports = CtrlStore;