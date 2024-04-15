run: 
	javac -sourcepath ./src/ -d ./target/ ./src/Main.java && cd ./target/ && java Main

# TODO: clean after run

docs:
	javadoc -d docs -sourcepath src -subpackages jbomberman

clean:
	rm src/*.class

