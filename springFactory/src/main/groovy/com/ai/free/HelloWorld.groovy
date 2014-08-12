 def var="hello "+
		"world"+
		",groovy!"
def repeat(val,repeat=3){
	for(i in 0..<repeat){
		println "This is ${i}:${repeat}:${val}"
	}
}
repeat(var)