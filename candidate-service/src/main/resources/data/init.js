db=db.getSiblingDB("candidate");

db.createUser({
    "user":"admin",
    "pwd":"123",
    "roles":[{"role":"readWrite","db":"candidate"}]
});
db.createCollection("candidate");

db.candidate.insertMany(
    [
        {_id:"1", name:"sagar",age:21,skills:["c","python"]},
        {_id:"2", name:"rama",age:22,skills:["java","spring"]},
        {_id:"3", name:"shyam",age:23,skills:["c++","nodejs"]}
    ]
)