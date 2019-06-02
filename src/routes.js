//INIT EXPRESS INIT DBCONNECTION
var express=require('express');
const conectDB= require('./backend/utils/conectdb');

var conection = conectDB.getConection();
const router = express.Router();


//IMPORT CONTROLLERS
const ctrlLogin = require('./backend/controllers/ctrlLogin');
const ctrlAddUsr = require('./backend/controllers/ctrladdUser');
const ctrlUser = require('./backend/controllers/ctrlUser');
const ctrlAddPet = require('./backend/controllers/ctrlAddPetWin');
const ctrlPetInfoWin=require('./backend/controllers/ctrlpetInfoWin');
const ctrlStore=require('./backend/controllers/ctrlStore');
const ctrlCarWin=require('./backend/controllers/ctrlCarrito');

//IMPORT MODELS AND INIZILICE THEM
const Usermod = require('./backend/models/user');
const petMod = require('./backend/models/pet');
const prodMod =require('./backend/models/product');
const reserveMod =require('./backend/models/reserve');
const dateMod=require('./backend/models/date');
const prodreservedMod= require('./backend/models/productsReserved');

const user=Usermod.getUser(conection);
const pet=petMod.getPet(conection, user);
const reserve=reserveMod.getReserve(conection, user);
const prod=prodMod.getProduct(conection, user);
const prodreserved =prodreservedMod.getProductRes(conection, reserve, prod);
const date=dateMod.getDate(conection, pet);

//LOGIN

router.get('/',(req,res)=>{

  ctrlLogin.showlogin(res);

});

router.post('/loginuser', (req,res) => {
            
  ctrlLogin.login(req,res,user,pet)

});

router.get('/showaddUser',(req,res)=>{

  ctrlAddUsr.showaddUser(res);

});


//ADDUSER

router.post('/addUser', (req, res, next) => {

  ctrlAddUsr.addUser(req,res,user,pet);

});


//USER

router.get('/showaddPet', (req, res) => {

  ctrlUser.showaddPetWin(req,res, pet);

});

router.post('/showPetInfo', (req, res) => {

  ctrlUser.showPetInfo(req,res, pet, date);

});

router.get('/store', (req, res) => {

  ctrlUser.showMarket(req,res,prod);

});

router.get('/logout', function(req, res, next) {

    res.render('login');

});



//ADDPET

router.post('/addPet', (req, res) => {

  ctrlAddPet.addPet(req,res, pet);

});


//PETINFOWINDOW

router.post('/addCita', (req, res) => {

  ctrlPetInfoWin.addCita(req,res, date);

});

router.post('/borrarPet', (req, res) => {

  ctrlPetInfoWin.deletePet(req,res, pet, date);

});

router.post('/borrarCita', (req, res) => {

  ctrlPetInfoWin.borraCita(req,res,pet, date);

});


//STORE


router.get('/ElementToBuy', (req, res) => {

  ctrlStore.showCarritoWin(req,res);

});

router.post('/AddPrd', (req, res) => {
  var pos= req.query.id;
  ctrlStore.addProdLista(req,res,pos);

});


//CARRITOCOMPRA

router.post('/borrarProd', (req, res) => {

  var pos= req.query.id;

  ctrlCarWin.borrarprod(req,res,pos);

});

router.post('/makeBuy', (req, res) => {

  ctrlCarWin.makeBuy(req,res,reserve,prodreserved,prod);

});

module.exports = router;
