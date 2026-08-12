db=db.getSiblingDB("job");
db.createUser({
    user: "mahir",
    pwd: "1234",
    roles:[{role:"readWrite",db:"job"}]
});

db.createCollection("job");

db.job.insertMany([
    {
        "description":"data engineer",
        "company":"exed",
        "skills":["python","sql"],
        "salary":3000,
        "isRemote":true
    },
    {
        "description":"data scientist",
        "company":"exed",
        "skills":["python","sql"],
        "salary":3000,
        "isRemote":true
    }
])