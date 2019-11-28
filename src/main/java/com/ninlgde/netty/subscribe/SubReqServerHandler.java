package com.ninlgde.netty.subscribe;

import com.ninlgde.netty.protobuf.SubscribeReqProto;
import com.ninlgde.netty.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter
{
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
	{
		SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
		if ("machicheng".equalsIgnoreCase(req.getUsername()))
		{
			System.out.println("Service accept client subscribe req:[" + req.toString() + "]");
			ctx.writeAndFlush(resp(req.getSubReqID()));
		}
	}

	private SubscribeRespProto.SubscribeResp resp(int subReqID)
	{
		SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
		builder.setSubReqID(subReqID);
		builder.setRespCode(0);
		builder.setDesc("Netty book order succeed. 3 days later, sent to the designated address");
		return builder.build();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	{
		cause.printStackTrace();
		ctx.close();
	}
}
