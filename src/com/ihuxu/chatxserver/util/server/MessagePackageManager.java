package com.ihuxu.chatxserver.util.server;

import java.util.LinkedList;
import java.util.Queue;

import com.ihuxu.chatxserver.common.model.MessagePackage;

public class MessagePackageManager {
	private static MyQueue<MessagePackage> chatTextMessagePackageQueue = new MyQueue<MessagePackage>();
	
	public static void pushMessgagePackageToQueue(int type, MessagePackage messagePackage) throws Exception {
		switch(type) {
		case MessagePackage.TYPE_CHAT_TEXT_MSG:
			MessagePackageManager.chatTextMessagePackageQueue.offer(messagePackage);
			break;
		default:
			throw new Exception("unknown message package type:" + type);
		}
	}
	
	public static MessagePackage popMessagePackageFromQueue(int type) throws Exception {
		switch(type) {
		case MessagePackage.TYPE_CHAT_TEXT_MSG:
			return MessagePackageManager.chatTextMessagePackageQueue.remove();
		default:
			throw new Exception("unknown message package type:" + type);
		}
	}
	
	public static MyQueue<MessagePackage> getMessagePackageQueue(int type) throws Exception {
		switch(type) {
		case MessagePackage.TYPE_CHAT_TEXT_MSG:
			return MessagePackageManager.chatTextMessagePackageQueue;
		default:
			throw new Exception("unknown message package type:" + type);
		}
	}
	
	public static void writeMessagePackages(int type) throws Exception {
		switch(type) {
		case MessagePackage.TYPE_CHAT_TEXT_MSG:
			/**
			 * TODO add lock to the queue.
			 */
			while(MessagePackageManager.chatTextMessagePackageQueue.empty() == false) {
				System.out.println("the current chatTextMessagePackageQueue size is:" + MessagePackageManager.chatTextMessagePackageQueue.storage.size());
				MessagePackage mP = MessagePackageManager.popMessagePackageFromQueue(MessagePackage.TYPE_CHAT_TEXT_MSG);
				ClientThread cT = ClientThreadManager.getClientThread(Long.toString(mP.getTextMessage().getTo()));
				cT.writeMessagePackage(mP);
			}
			break;
		default:
			throw new Exception("unknown message package type:" + type);
		}
	}

	private static class MyQueue<T> {
	    private Queue<T> storage = new LinkedList<T>();

	    /** 将指定的元素插入队尾 */
	    public void offer(T v) {
	        storage.offer(v);
	    }

	    /** 检索，但是不移除队列的头，如果此队列为空，则返回 null */
	    public T peek() {
	        return storage.peek();
	    }

	    /** 检索，但是不移除此队列的头 */
	    /** 此方法与 peek 方法的惟一不同是，如果此队列为空，它会抛出一个异常 */
	    public T element() {
	        return storage.element();
	    }

	    /** 检索并移除此队列的头，如果队列为空，则返回 null */
	    public T poll() {
	        return storage.poll();
	    }

	    /** 检索并移除此队列的头 */
	    /** 此方法与 poll 方法的不同在于，如果此队列为空，它会抛出一个异常 */
	    public T remove() {
	        return storage.remove();
	    }

	    /** 队列是否为空 */
	    public boolean empty() {
	        return storage.isEmpty();
	    }

	    /** 打印队列元素 */
	    public String toString() {
	        return storage.toString();
	    }
	}


}
