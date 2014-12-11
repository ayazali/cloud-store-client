cloud-store
============

### File Syncing Software


cloud-store is a file syncing software built using [Apache Commons VFS](http://commons.apache.org/proper/commons-vfs/)

- Spring 4.x
- Hibernate
- SiteMesh
- AWS

Currently file syncing is built upon two protocols
- Local File System
- SFTP

Parent Project available [cloud-store](https://github.com/hasnain110/cloud-store)

Build & Run by simply running
`mvn clean install`

>Some builds may encounter issues with dependecies of `commons-vfs2-sandbox`. Install the library from this [repository](https://maven-us.nuxeo.org/nexus/content/repositories/public/org/apache/commons/commons-vfs2-sandbox/2.0/)
