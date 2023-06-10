var status = rs.status();
if (status.errmsg === 'no replset config has been received') {
  rs.initiate();
}
for (var i = 1; i <= param; i++) {
  if (i!==1)
    rs.add(folder+"_exhibition-mongodb-node_" + i + ":27018");
}
var cfg = rs.conf();
cfg.members[0].host = folder+"_exhibition-mongodb-node_1:27018";
rs.reconfig(cfg);
