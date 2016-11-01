# boot-web

A Clojure web project, built with boot, to use to test out build/deploy systems.

## Usage

Run the project directly:

    $ boot run

Build an uberjar from the project:

    $ boot build

Run the uberjar:

    $ java -jar target/examples/boot-web-{version}.jar




Alternatively, run with Docker:

    docker run --volume ~/.m2:/root/.m2 --volume $(pwd):/opt boot-build boot build

Note the above uses the local Maven repository cache on your host machine. Otherwise, each time this image was removed and re-run, it would download all the Maven dependencies for the project.

You would first need to create the boot-build image using the Dockerfile provided.

