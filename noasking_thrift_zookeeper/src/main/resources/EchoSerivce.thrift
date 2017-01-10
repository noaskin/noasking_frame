namespace java com.noasking.thrift.rpc.demo
service EchoSerivce
{
	string echo(1: string msg);
}