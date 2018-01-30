# boot-web

A Clojure web project, built with boot, to use to test out build/deploy systems.

Produces a web page with the version and a coloured background. Each version has a different colour.

## Usage

### Build and run with Boot locally installed

Build an uberjar from the project:

    $ boot build
    
or run with the script

    $ ./build.sh

Then you can run the resulting JAR locally

Run the uberjar:

    $ java -jar target/examples/boot-web-{version}.jar

Or, without building, just run the project directly:

    $ boot run
    
### Run with Docker

Run the script:

    $ ./run.sh
    
This first ensures you have a Docker image locally with Boot installed and set as the entrypoint, then runs something like this:

    docker run --volume ~/.m2:/root/.m2 --volume $(pwd):/opt boot-build boot build

Note the above uses the local Maven repository cache on your host machine. Otherwise, each time this image was removed and re-run, it would download all the Maven dependencies for the project.

