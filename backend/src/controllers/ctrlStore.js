const ctrlUser= require('./ctrlUser');
const ctrlCarrito =require('../controllers/ctrlCarWin');
var express=require('express');
class CtrlStore{

    constructor(route, dir, user, res, prod, req, pet, prodreserved,reserve){

        this.prodsToBuy = [];

        this.dir=dir;
        this.User=user;
        this.prod=prod;
        this.reserve=reserve;
        this.prodreserved=prodreserved;
        this.pet=pet;
        this.prodsA=[];

        this.prod.findAll({model:this.prod}).then(prods => { 
                this.prodsA=prods;
                res.render(dir + '/views/store', {prods});
        }).catch(error => {
            console.log(error);
        });

        route.post("/AddPrd", (req,res)=>{

            var i=0;

            while(this.prodsToBuy[i]!=null){
                i++;
            }

            this.prodsToBuy[i]=this.prodsA[req.query.id];
            
        });

        route.get('/ElementToBuy', (req,res) => {

            var ctrlCarWin = new ctrlCarrito(route, res, this.dir, this.User, this.reserve, this.prodreserved, this.prodsToBuy);

        });


    }

}

module.exports = CtrlStore;